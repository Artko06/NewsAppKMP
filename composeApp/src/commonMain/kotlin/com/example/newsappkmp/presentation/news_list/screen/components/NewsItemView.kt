package com.example.newsappkmp.presentation.news_list.screen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.example.newsappkmp.domain.model.NewsItem
import com.example.newsappkmp.presentation.animation.PulseAnimation
import com.example.newsappkmp.presentation.util.LightBlue
import neswsappkmp.composeapp.generated.resources.Res
import neswsappkmp.composeapp.generated.resources.arrow_forward_ios
import neswsappkmp.composeapp.generated.resources.news_error
import org.jetbrains.compose.resources.painterResource

@Composable
fun NewsItemView(
    item: NewsItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Surface(
        modifier = modifier
            .clip(shape = RoundedCornerShape(32.dp))
            .clickable(onClick = onClick),
        color = LightBlue.copy(alpha = 0.2f)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                var imageLoadResult by remember {
                    mutableStateOf<Result<Painter>?>(null)
                }

                val painter = rememberAsyncImagePainter(
                    model = item.urlToImage,
                    onSuccess = {
                        imageLoadResult =
                            if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                                Result.success(it.painter)
                            } else {
                                Result.failure(Exception("Invalid image size"))
                            }
                    },
                    onError = {
                        it.result.throwable.printStackTrace()
                        imageLoadResult = Result.failure(it.result.throwable)
                    }
                )

                val painterState by painter.state.collectAsStateWithLifecycle()
                val transition by animateFloatAsState(
                    targetValue = if (painterState is AsyncImagePainter.State.Success) {
                        1f
                    } else {
                        0f
                    },
                    animationSpec = tween(durationMillis = 800)
                )

                when (val result = imageLoadResult) {
                    null -> PulseAnimation(
                        modifier = Modifier.size(60.dp)
                    )

                    else -> {
                        Image(
                            painter = if (result.isSuccess) painter else {
                                painterResource(Res.drawable.news_error)
                            },
                            contentDescription = item.title,
                            contentScale = if (result.isSuccess) {
                                ContentScale.Crop
                            } else {
                                ContentScale.Fit
                            },
                            modifier = Modifier
                                .aspectRatio(
                                    ratio = 1f
                                )
                                .graphicsLayer {
                                    rotationY = (1f - transition) * 30f
                                    val scale = 0.8f + (0.2f * transition)
                                    scaleX = scale
                                    scaleY = scale
                                }
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.title ?: "Unknown title",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = item.author ?: "Unknown author",
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Icon(
                painter = painterResource(resource = Res.drawable.arrow_forward_ios),
                contentDescription = null,
                modifier = Modifier
                    .size(18.dp),
                tint = Color.Black
            )
        }
    }

//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(8.dp, 8.dp).background(Color.White),
//        verticalAlignment = Alignment.Top,
//        horizontalArrangement = Arrangement.SpaceAround
//    ) {
//        Box(
//            modifier = Modifier
//                .width(120.dp)
//                .height(120.dp).padding(0.dp, 0.dp, 8.dp, 0.dp)
//        ) {
//            Image(
//                painter = rememberAsyncImagePainter(item.urlToImage.orEmpty()),
//                contentDescription = "imageNews",
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop
//            )
//
//            IconButton(onClick = { onClick.invoke() }) {
//                Icon(
//                    painter = painterResource(resource = Res.drawable.ic_star_smile),
//                    contentDescription = "favorite",
//                    tint = Color.Yellow,
//                    modifier = Modifier.width(50.dp).height(50.dp),
//                )
//            }
//        }
//
//        Column {
//            Text(text = item.title.orEmpty(), style = MaterialTheme.typography.titleMedium)
//
//            Text(
//                text = item.content.orEmpty(),
//                style = MaterialTheme.typography.titleSmall,
//                maxLines = 3
//            )
//
//            Text(text = item.publishedAt.orEmpty(), style = MaterialTheme.typography.bodyMedium)
//        }
}