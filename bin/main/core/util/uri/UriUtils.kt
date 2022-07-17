package core.util.uri

import java.net.URI

internal fun String.toUri(): URI = URI(this)