package org.cccsharonparish.feature.permission

import cafe.adriel.voyager.core.screen.Screen

interface IPermissionScreen:Screen{
    var nextScreen:Screen?
    var minSDKVersion:Int?
    var permission:String?
}

expect fun getPermissionScreen(permissionUIState: PermissionUIState): IPermissionScreen