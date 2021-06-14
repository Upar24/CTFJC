package com.example.ctf240521.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ctf240521.data.local.entities.Post
import com.example.ctf240521.ui.component.ButtonClickItem
import com.example.ctf240521.ui.component.ProgressBarItem
import com.example.ctf240521.ui.screens.post.PostViewModel
import com.example.ctf240521.util.Constants
import com.example.ctf240521.util.Constants.KEY_LOGGED_IN_USERNAME
import com.example.ctf240521.util.Constants.NO_USERNAME
import com.example.ctf240521.util.Event
import com.example.ctf240521.util.Status
import com.example.ctf240521.viewmodel.RegisterViewModel

@Composable
fun HomeScreen(
    vm: RegisterViewModel = viewModel(),
    vm1: PostViewModel = viewModel(),
){
    Column(){
        Text("Home Screen Hmm LOL${vm.passwordvm} ${vm.usernamevm} " +
                (vm.sharedPref.getString(KEY_LOGGED_IN_USERNAME,NO_USERNAME) ?: NO_USERNAME)
        )
        ButtonClickItem(desc = "Load all posts", onValidate = {vm1.getAllPosts()})
        ButtonClickItem(desc = "Post from following", onValidate = {
            vm1.getFollowingPosts(vm.sharedPref.getString(KEY_LOGGED_IN_USERNAME, NO_USERNAME) ?: NO_USERNAME) })
    }
    
    if(vm.isLoggedIn()){
        vm.usernamevm?.let { vm.passwordvm?.let { it1 -> vm.authenticateApi(it, it1) } }
    }
    val uiState= vm1.post.observeAsState()
    uiState.value?.let {
        val result = it.peekContent()
        when(result.status){
            Status.SUCCESS -> {
                PostListItem(post = result.data)
            }
            Status.ERROR -> {
                Toast.makeText(
                    LocalContext.current,
                    result.message ?: "An unknown error occured",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            Status.LOADING -> {
                ProgressBarItem()
            }
        }
    }
}
@Composable
fun PostListItem(post: List<Post>?){
    post?.forEach {
    Row {
        Column() {
            Text(text = it._id)
            Text(text = it.title)
        }
    }
    }
}