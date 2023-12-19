# Demo of Sora Editor for Jetpack Compose

This repository is a simple project which demonstrates how to embed Sora Editor library's Code Editor into a Jetpack Compose project.

**Note:** The special libraries haven't been put into version catalog

## Use an `AndroidView`

Basically, you will need to use an `AndroidView` to define a `CodeEditor`. You can either inflate the editor XML layout, or directly create a `CodeEditor` instance like following.

```kotlin
@Composable
fun CodeEditor(
    modifier: Modifier = Modifier,
    properties: ((CodeEditor) -> Unit)? = null
) {
    val context = LocalContext.current
    val editor = remember {
        CodeEditor(context)
            .also {
                properties?.invoke(it)
            }
    }
    AndroidView(
        factory = { editor },
        modifier = modifier.fillMaxSize(),
        onRelease = { it.release() }
    )
}
```

## Libraries used

- [ComposePreference](https://github.com/zhanghai/ComposePreference)
- [sora-editor](https://github.com/Rosemoe/sora-editor)