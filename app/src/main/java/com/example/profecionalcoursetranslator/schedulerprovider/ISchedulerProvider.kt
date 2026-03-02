package com.example.profecionalcoursetranslator.schedulerprovider
import io.reactivex.Scheduler

//In the sake of testing
interface ISchedulerProvider {

    fun ui(): Scheduler
    fun io(): Scheduler
}
