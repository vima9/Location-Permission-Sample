package com.dbab.locationpermissionsample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.dbab.locationpermissionsample.ui.theme.LocationPermissionSampleTheme

private const val TAG = "MainActivity"


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {

            val scaffoldState = rememberScaffoldState()
            val permissionTestViewModel = PermissionTestViewModel()
            LocationPermissionSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {


                    //Note Scaffold makes certain things easier in the long run
                    // you can remove it based on your app needs
                    Scaffold(
                        topBar = {


                        },
                        scaffoldState = scaffoldState,
                        snackbarHost = { scaffoldState.snackbarHostState })
                    { innerPadding ->

                        Box(modifier = Modifier.padding(innerPadding)) {

                            PermissionTestUI(scaffoldState, permissionTestViewModel)

                            DefaultSnackbar(
                                snackbarHostState = scaffoldState.snackbarHostState,
                                modifier = Modifier.align(Alignment.BottomCenter),
                                onAction = {
                                    Log.d(TAG, "Snackbar action performed")
                                    scaffoldState.snackbarHostState.currentSnackbarData?.performAction()
                                },
                            )
                        }
                    }


                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LocationPermissionSampleTheme {

    }
}