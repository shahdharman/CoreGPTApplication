package com.example.coregpt.screens.mynotesScreens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.coregpt.R
import com.example.coregpt.database.MyNote
import com.example.coregpt.util.uiUtil.MyTopAppBar
import com.example.coregpt.util.uiUtil.SelectCategory
import com.example.coregpt.viewmodel.CoreGPTViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyNotesScreen(coreGPTViewModel: CoreGPTViewModel, onClick: () -> Unit) {


    var question by remember {
        mutableStateOf("")
    }
    var answer by remember {
        mutableStateOf("")
    }

    var selectedNote by remember {
        mutableStateOf<MyNote?>(null)
    }

    val context = LocalContext.current

    val selectedOption = remember { mutableStateOf("OS") }

    val myNoteList =
        coreGPTViewModel.getMyNoteByCategory(category = selectedOption.value)
            .collectAsState().value

    val openDialog = remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            MyTopAppBar(
                screenName = R.string.myNoteScreen,
                icon = Icons.Default.ArrowBack,
                color = Color.Black
            ) {
                onClick()
            }
        },
        floatingActionButton = {
            IconButton(
                onClick = { openDialog.value = true }, modifier = Modifier
                    .clip(shape = RoundedCornerShape(30.dp))
                    .background(Color(249, 168, 37, 255))
                    .size(60.dp)
            )
            {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = "Add Note",
                    modifier = Modifier.size(40.dp), tint = Color.Black
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color.Transparent
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SelectCategory(
                    selectedOption = selectedOption.value,
                    onOptionSelected = { selectedOption.value = it })

                if (myNoteList.isEmpty()) {
                    Text(
                        text = "No Notes is available...\n\nClick on Add icon to add ",
                        modifier = Modifier.padding(vertical = 220.dp),
                        color = Color.Gray,
                        fontStyle = FontStyle.Italic
                    )

                } else {
                    LazyColumn {
                        items(myNoteList) { myNote ->

                            MyNoteRow(myNote = myNote,
                                onUpdateClicked = {
                                    selectedNote = myNote
                                    question = selectedNote!!.question
                                    answer = selectedNote!!.answer
                                    openDialog.value = true
                                },
                                onDeleteClicked = {
                                    coreGPTViewModel.delete(myNote)
                                })


                        }
                    }
                }


            }
        }
        if (openDialog.value) {

            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(
                        text = "Write Your Note:",
                        fontFamily = FontFamily.Default,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                text =
                {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 15.dp, end = 15.dp, top = 50.dp),
                                verticalArrangement = Arrangement.spacedBy(20.dp)
                            )
                            {
                                OutlinedTextField(
                                    value = question, onValueChange = { question = it },
                                    modifier = Modifier
                                        .heightIn(min = 75.dp, max = 150.dp)
                                        .fillMaxWidth(),
                                    label = {
                                        Text(text = "Question")
                                    },
                                    placeholder = {
                                        Text(text = "Type question here...")
                                    },
                                    singleLine = false,
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        containerColor = Color.White,
                                        textColor = Color.Black,
                                        focusedBorderColor = Color(86, 219, 91, 255),
                                        unfocusedBorderColor = Color.Black
                                    )
                                )

                                OutlinedTextField(
                                    value = answer, onValueChange = { answer = it },
                                    modifier = Modifier
                                        .heightIn(min = 100.dp, max = 300.dp)
                                        .fillMaxWidth(),
                                    label = {
                                        Text(text = "Answer")
                                    },
                                    placeholder = {
                                        Text(text = "Type answer here...")
                                    },
                                    singleLine = false,
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        containerColor = Color.White,
                                        textColor = Color.Black,
                                        focusedBorderColor = Color(86, 219, 91, 255),
                                        unfocusedBorderColor = Color.Black
                                    )
                                )

                                SelectCategory(
                                    selectedOption = selectedOption.value,
                                    onOptionSelected = {
                                        selectedOption.value = it
                                    })

                            }
                        }
                    }
                },
                confirmButton = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Button(
                            onClick = {
                                if (question.isNotEmpty() && answer.isNotEmpty()) {

                                    if (selectedNote != null) {
                                        selectedNote!!.question = question
                                        selectedNote!!.answer = answer
                                        selectedNote!!.category = selectedOption.value

                                        coreGPTViewModel.update(selectedNote!!)
                                        selectedNote = null
                                        Toast.makeText(context, "Note Updated", Toast.LENGTH_SHORT)
                                            .show()

                                    } else {
                                        val myNote = MyNote(
                                            question = question,
                                            answer = answer,
                                            category = selectedOption.value
                                        )
                                        coreGPTViewModel.insert(myNote)
                                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                    question = ""
                                    answer = ""
                                    openDialog.value = false
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Text field cannot be empty",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(249, 168, 37, 255)
                            ),
                            elevation = ButtonDefaults.buttonElevation(2.dp),
                        )
                        {
                            Text(text = "Save", color = Color.Black)
                        }
                    }
                },
                containerColor = Color.White,
                titleContentColor = Color.Black,
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = false
                )
            )
        }

    }


}


@Composable
fun MyNoteRow(
    myNote: MyNote,
    onUpdateClicked: (MyNote) -> Unit,
    onDeleteClicked: (MyNote) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(0.5.dp, Color.Gray, RoundedCornerShape(10.dp))
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(223, 243, 200, 255)),
    )
    {

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 6.dp)
        )
        {
            Text(
                text = "Ques: ${myNote.question}",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily.Default
            )

            if (expanded) {
                Text(
                    text = "${myNote.answer}",
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily.Default,
                    lineHeight = 20.sp
                )

            } else {
                Text(
                    text = "Tap to expand...",
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.ExtraLight
                )
            }


            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            )
            {
                IconButton(onClick = {
                    onUpdateClicked(myNote)
                })
                {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_edit_note_24),
                        contentDescription = "EDIT NOTE"
                    )
                }
                IconButton(onClick = {
                    onDeleteClicked(myNote)
                })
                {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Note",
                        tint = Color(0xFFb6042a)
                    )
                }

            }
        }
    }

}
