/*
 * Copyright (C) 2020 The Android Open Source Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.people.data

import androidx.core.net.toUri


sealed class Contact(
    val id: Long,
    val icon: String
) {
    val name: String = this::class.simpleName!!
    val iconUri = "content://com.example.android.people/icon/$id".toUri()
    val shortcutId = "contact_$id"
    protected val defaultMessage get() = Message(
        sender = this.id,
        timestamp =System.currentTimeMillis()
    )
    abstract fun reply(text: String): Message


    object Cat:Contact(1,"cat.jpg") {
        override fun reply(text: String): Message
            = defaultMessage.copy(text = "Meow")
    }

    object Dog:Contact(2,"dog.jpg") {
        override fun reply(text: String)
            = defaultMessage.copy(text = "Woof woof!!")
    }

    object Parrot:Contact(3,"parrot.jpg") {
        override fun reply(text: String): Message
            = defaultMessage.copy(text = text)
    }

    object Sheep:Contact(4,"sheep.jpg") {
        override fun reply(text: String): Message
            = defaultMessage.copy(
                text = "Look at me!",
                photoUri = "content://com.example.android.people/photo/sheep_full.jpg".toUri(),
                photoMimeType = "image/jpeg",
            )
    }

    companion object {
        val CONTACTS = listOf(
            Cat,Dog,Parrot,Sheep
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Contact) return false

        if (id != other.id) return false
        if (icon != other.icon) return false
        if (name != other.name) return false
        if (iconUri != other.iconUri) return false
        if (shortcutId != other.shortcutId) return false

        return true
    }
    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + icon.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + iconUri.hashCode()
        result = 31 * result + shortcutId.hashCode()
        return result
    }
}
