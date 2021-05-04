package com.condor.usecases.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/*
Esta regla nos permite controlar
 en distintos puntos de los test
  lo que se quiere que ocurra
 */
@ExperimentalCoroutinesApi
class MainCoroutineScopeRule(val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    TestWatcher() {

    //Antes de iniciar el test
    override fun starting(description: Description?) {
        super.starting(description)
        //Nos permite modificar el main
        Dispatchers.setMain(dispatcher)
    }

    //Despues de finalizar el test
    override fun finished(description: Description?) {
        super.finished(description)
        // Si en una coroutina se
        // queda enganchado un test es necesario limpiarlo.
        dispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

}