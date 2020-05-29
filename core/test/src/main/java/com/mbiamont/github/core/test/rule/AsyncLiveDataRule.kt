package com.mbiamont.github.core.test.rule

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class AsyncLiveDataRule : TestRule {

    //Rule needed when you need to test some classes using LiveData such ViewModel
    //https://github.com/spekframework/spek/issues/337

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
                    override fun executeOnDiskIO(runnable: Runnable) {
                        runnable.run()
                    }

                    override fun isMainThread(): Boolean {
                        return true
                    }

                    override fun postToMainThread(runnable: Runnable) {
                        runnable.run()
                    }
                })

                try {
                    base?.evaluate()
                } finally {
                    ArchTaskExecutor.getInstance().setDelegate(null)
                }
            }
        }
    }
}