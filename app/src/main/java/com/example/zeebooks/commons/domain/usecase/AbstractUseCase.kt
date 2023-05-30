package com.example.zeebooks.commons.domain.usecase

import com.example.zeebooks.commons.viewmodel.model.DataModel
import com.example.zeebooks.commons.viewmodel.model.DataStatus
import com.example.zeebooks.commons.viewmodel.model.ExecutionCase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


abstract class AbstractUseCase<I, O> : ExecutionCase<DataModel<O>> {

    @Inject
    lateinit var dispatcher: CoroutineDispatcher

    @Inject
    lateinit var gson: Gson



    abstract fun execute(args: I): Flow<DataModel<O>>

    protected fun flow(block: suspend () -> DataModel<O>): Flow<DataModel<O>> {
        return kotlinx.coroutines.flow.flow {
            val result = block().apply {
                status = this.status ?: DataStatus.SUCCESS

            }
            emit(result)
        }.onStart {
            emit(DataModel(status = DataStatus.LOADING))
        }.flowOn(dispatcher)
    }
}
