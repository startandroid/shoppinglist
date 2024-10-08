package ru.startandroid.shoppinglist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.startandroid.core.navigation.NavScreen
import ru.startandroid.core.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    val navigator: Navigator,
    val navScreens: MutableSet<NavScreen>
) : ViewModel() {


}