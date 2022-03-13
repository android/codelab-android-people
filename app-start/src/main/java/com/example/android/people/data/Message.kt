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

import android.net.Uri

data class Message(
    val id: Long = -1,
    val sender: Long = -1,
    val text: String = "",
    val photoUri: Uri? = null,
    val photoMimeType: String? = null,
    val timestamp: Long = -1
) {

    val isIncoming: Boolean
        get() = sender != 0L
    val isAvailable
        get() = id!= -1L && sender !=- 1L && timestamp != -1L
}
