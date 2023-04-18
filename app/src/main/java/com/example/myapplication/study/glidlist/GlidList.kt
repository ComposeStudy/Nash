package com.example.myapplication.study.glidlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.study.glidlist.theme.AffirmationTheme

@Composable
fun GridMainView() {
    AffirmationTheme {
        GridRow()
    }
}

@Composable
fun GridRow() {

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(6.dp)
//    ) {
//        Image(painter = painterResource(id = R.drawable.image1), contentDescription = "")
//        Text(text = "레오는 현술조가 되었나")
//    }

    Row(modifier = Modifier.padding(5.dp), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        GridItem(1,1,1, Modifier.weight(1f))
        GridItem(1, 1, 1, Modifier.weight(1f))
    }

}

@Composable
fun GridItem(imageId: Int, stringId: Int, count: Int, weight: Modifier) {
    Surface(shadowElevation = 8.dp, shape = RoundedCornerShape(8.dp), modifier = weight) {
        Box {
            Image(
                modifier = Modifier
                    .size(68.dp)
                    .align(Alignment.TopStart),
                painter = painterResource(id = R.drawable.architecture),
                contentDescription = "architecture"
            )

            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 84.dp, top = 0.dp, end = 16.dp, bottom = 8.dp),
                text = stringResource(id = R.string.architecture),
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 84.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Icon(
                    modifier = Modifier.size(12.dp),
                    painter = painterResource(id = R.drawable.image1),
                    contentDescription = "icon"
                )

                Text(
                    text = "321",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}
