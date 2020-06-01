package com.mbiamont.github.service.mapper

import com.mbiamont.github.domain.entity.Fork
import com.mbiamont.github.service.graphql.FetchRepositoryForksQuery
import java.util.*

class RemoteForkMapper(
    private val dateMapper: IRemoteDateMapper,
    private val userMapper: IRemoteUserMapper
) : IRemoteForkMapper {

    override fun map(fork: FetchRepositoryForksQuery.Node) = Fork(
        id = fork.id(),
        createdAt = dateMapper.mapToCalendar(fork.createdAt() as? String ?: "") ?: Calendar.getInstance(),
        owner = userMapper.map(fork.owner())
    )
}