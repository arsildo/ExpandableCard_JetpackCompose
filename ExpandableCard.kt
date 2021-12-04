import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exchangeratesconversion.ui.theme.Purple700

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard(
    header: String, // Header
    description: String, // Description
) {
    var expand by remember { mutableStateOf(false) } // Expand State
    val rotationState by animateFloatAsState(if (expand) 180f else 0f) // Rotation State
    var stroke by remember { mutableStateOf(1) } // Stroke State
    Card(
        modifier = Modifier
            .animateContentSize( // Animation
                animationSpec = tween(
                    durationMillis = 400, // Animation Speed
                    easing = LinearOutSlowInEasing // Animation Type
                )
            )
            .padding(8.dp),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp), // Shape
        border = BorderStroke(stroke.dp, Purple700), // Stroke Width and Color
        onClick = {
            expand = !expand
            stroke = if (expand) 2 else 1
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // Control the header Alignment over here.
            ) {
                Text(
                    text = header,
                    color = Purple700, // Header Color
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.weight(.9f).padding(start = 8.dp)
                )
                IconButton(
                    modifier = Modifier
                        .rotate(rotationState)
                        .weight(.1f),
                    onClick = {
                        expand = !expand
                        stroke = if (expand) 2 else 1
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        tint = Purple700, // Icon Color
                        contentDescription = "Drop Down Arrow"
                    )
                }
            }
            if (expand) {
                Text(
                    text = description,
                    color = Purple700, // Description Color
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
        }
    }

}