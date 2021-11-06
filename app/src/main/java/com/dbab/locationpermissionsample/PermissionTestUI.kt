package com.dbab.locationpermissionsample

import android.Manifest
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.dbab.locationpermissionsample.permission.PermissionAction
import com.madhu.locationpermission.permission.PermissionUI
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier


private const val TAG = "PermissionTestUI"

@Composable
fun PermissionTestUI(scaffoldState: ScaffoldState, permissionTestViewModel: PermissionTestViewModel) {



    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val performLocationAction by permissionTestViewModel.performLocationAction.collectAsState()



    if (performLocationAction) {
        Log.d(TAG, "Invoking Permission UI")

        PermissionUI(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION,
            stringResource(id = R.string.permission_location_rationale),
            scaffoldState
        ) { permissionAction ->
            when (permissionAction) {
                is PermissionAction.OnPermissionGranted -> {
                    permissionTestViewModel.setPerformLocationAction(false)
                    //Todo: do something now as we have location permission
                    Log.d(TAG, "Location has been granted")
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("Location permission granted!")
                    }
                }
                is PermissionAction.OnPermissionDenied -> {
                    permissionTestViewModel.setPerformLocationAction(false)
                }
            }
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Text("Permission Test")

        Button(onClick = { permissionTestViewModel.setPerformLocationAction(true) }) {
            Text("Capture Location")
        }
    }


}