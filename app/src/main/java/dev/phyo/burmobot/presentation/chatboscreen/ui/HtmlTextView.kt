package dev.phyo.burmobot.presentation.chatboscreen.ui

import android.graphics.Color
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun HtmlTextView(htmlContent: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = false
                settings.defaultTextEncodingName = "utf-8"
                setBackgroundColor(Color.TRANSPARENT)
            }
        },
        update = { webView ->
            webView.post {
                val styledHtmlContent = """
                    <html>
                    <head>
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <style>
                            body { color: white; background-color: #6200EE; font-size: 16px; margin: 0; padding: 8px; }
                            b, i { color: white; }
                        </style>
                    </head>
                    <body>
                        $htmlContent
                    </body>
                    </html>
                """.trimIndent()

                if (webView.url == null) {
                    webView.loadDataWithBaseURL(null, styledHtmlContent, "text/html", "utf-8", null)
                }
            }
        }
    )
}