package com.harrypotter.coreui.dispatchers

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoroutinesDispatchersImpl @Inject constructor(): CoroutinesDispatchers {
    override val immediate = Dispatchers.Main.immediate
    override val main = Dispatchers.Main
    override val io = Dispatchers.IO
}
