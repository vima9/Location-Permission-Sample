package com.dbab.locationpermissionsample.permission

sealed class PermissionAction {

    object OnPermissionGranted : PermissionAction()

    object OnPermissionDenied : PermissionAction()
}