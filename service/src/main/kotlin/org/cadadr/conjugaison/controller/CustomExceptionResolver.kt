package org.cadadr.conjugaison.controller

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.graphql.execution.ErrorType
import org.springframework.stereotype.Component

@Component
class CustomExceptionResolver : DataFetcherExceptionResolverAdapter() {

    override fun resolveToSingleError(
        ex: Throwable,
        env: DataFetchingEnvironment
    ): GraphQLError? = if (ex is VerbNotFoundException) {
        GraphqlErrorBuilder.newError().errorType(ErrorType.NOT_FOUND).message(ex.message)
            .path(env.executionStepInfo.path).location(env.field.sourceLocation).build()
    } else {
        null
    }

}
