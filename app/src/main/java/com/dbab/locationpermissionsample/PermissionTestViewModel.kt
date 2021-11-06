package com.dbab.locationpermissionsample

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PermissionTestViewModel : ViewModel() {
    private val _performLocationAction: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val performLocationAction = _performLocationAction.asStateFlow()


    fun setPerformLocationAction(request: Boolean) {
        _performLocationAction.value = request
    }

}