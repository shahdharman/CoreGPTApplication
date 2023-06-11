package com.example.coregpt.screens.mynotesScreens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.coregpt.database.MyNote
import com.example.coregpt.util.uiUtil.provideCategory
import com.example.coregpt.util.uiUtil.selectCategory
import com.example.coregpt.viewmodel.CoreGPTViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyNotesScreen(coreGPTViewModel: CoreGPTViewModel) {


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
            TopAppBar(
                title = { Text(text = "My Notes") }
            )
        },
        floatingActionButton = {
            IconButton(onClick =
            {
                openDialog.value = true
            })
            {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = "Add Note",
                    modifier = Modifier.size(50.dp), tint = Color.Red
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column() {
                selectCategory(
                    selectedOption = selectedOption.value,
                    onOptionSelected = { selectedOption.value = it })

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
        if (openDialog.value) {

            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "Dialog Title")
                },
                text =
                {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.White
                    ) {
                        LazyColumn {
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
                                        singleLine = false
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
                                        singleLine = false
                                    )

                                    selectCategory(
                                        selectedOption = selectedOption.value,
                                        onOptionSelected = {
                                            selectedOption.value = it
                                        })

                                    Text(
                                        text = "Selected Category: ${provideCategory(category = selectedOption.value)}",
                                        fontSize = 11.sp,
                                    )

                                }
                            }
                        }

                    }
                },
                confirmButton = {
                    Button(onClick = {
                        if (question.isNotEmpty() && answer.isNotEmpty()) {

                            if (selectedNote != null) {
                                selectedNote!!.question = question
                                selectedNote!!.answer = answer
                                selectedNote!!.category = selectedOption.value

                                coreGPTViewModel.update(selectedNote!!)
                                selectedNote = null
                                Toast.makeText(context, "Note Updated", Toast.LENGTH_SHORT).show()

                            } else {
                                val myNote = MyNote(
                                    question = question,
                                    answer = answer,
                                    category = selectedOption.value
                                )
                                coreGPTViewModel.insert(myNote)
                                Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                            }
                            question = ""
                            answer = ""
                            openDialog.value = false
                        }
                    }) {
                        Text(text = "Save")
                    }
                },

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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)),
        elevation = CardDefaults.cardElevation(2.dp)
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
                textAlign = TextAlign.Justify
            )

            Text(
                text = "Ans: ${myNote.answer}",
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            {
                Text(text = "Update",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.clickable
                    {
                        onUpdateClicked(myNote)

                    })
                Text(text = "Delete",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.clickable
                    {
                        onDeleteClicked(myNote)
                    })
            }
        }
    }


}
