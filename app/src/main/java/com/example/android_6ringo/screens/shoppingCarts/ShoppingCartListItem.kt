package com.example.android_6ringo.screens.shoppingCarts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_6ringo.entities.ShoppingCart
import com.example.android_6ringo.helpers.LONG_DATE_FORMAT


@Composable
fun ShoppingCartListItem(modifier: Modifier = Modifier, shoppingCart: ShoppingCart, onDelete: () -> Unit) {
    val deleteState = rememberShoppingCartDeleteState(shoppingCart = shoppingCart, onSave = {onDelete()})
    ShoppingCartDeleteDialog(viewModel = deleteState)
    Box(modifier = modifier) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(shoppingCart.game?.article?.images?.get(0)?.thumbnailUrl ?: "", "",
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.White)
                    .width(100.dp)
                    .aspectRatio(1.61f)
                )
            Spacer(Modifier.width(8.dp))
            Column(Modifier.weight(1f)) {
                Text(text = shoppingCart.game?.article?.name ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                val quantity = shoppingCart.quantity
                val totalPrice = (shoppingCart.game?.price ?:0) * shoppingCart.quantity
                Text(text = "$${shoppingCart.game?.price} × $quantity unité${if(quantity > 1) "s" else ""} = $$totalPrice",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )



                if(shoppingCart.game?.status?.uppercase() == "ON_GOING") {
                    Text(
                        text = "S'achève le ${shoppingCart.game?.endDate?.format(LONG_DATE_FORMAT)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(.5f)
                    )
                }else if(shoppingCart.game?.status?.uppercase() == "UPCOMING") {
                    Text(
                        text = "Débute le ${shoppingCart.game?.startDate?.format(LONG_DATE_FORMAT)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(.6f)
                    )
                }
            }

            Spacer(Modifier.width(16.dp))

            Column(verticalArrangement = Arrangement.Center) {
                IconButton(onClick = { deleteState.open() }) {
                    Icon(Icons.Outlined.Delete, "Delete cart item")
                }
            }
        }
    }
}