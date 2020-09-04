package com.will.bottomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.account_icon_layout.*
import kotlinx.android.synthetic.main.contact_icon_layout.*
import kotlinx.android.synthetic.main.explore_icon_layout.*
import kotlinx.android.synthetic.main.message_icon_layout.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //映射，字典,key,value 呈键值对出现
        val destinationMap = mapOf(
            R.id.messageFragment to messageMotionLayout,
            R.id.contactFragment to contactMotionLayout,
            R.id.exploreFragment to exploreMotionLayout,
            R.id.accountFragment to accountMotionLayout
        )

        navController =  findNavController(R.id.fragment)

        //设置底部导航与页面的联动,forEach循环遍历
        destinationMap.forEach {map ->
            map.value.setOnClickListener { navController.navigate(map.key) }

        }
//        messageMotionLayout.setOnClickListener { navController.navigate(R.id.messageFragment) }
//        contactMotionLayout.setOnClickListener { navController.navigate(R.id.contactFragment) }
//        exploreMotionLayout.setOnClickListener { navController.navigate(R.id.exploreFragment) }
//        accountMotionLayout.setOnClickListener { navController.navigate(R.id.accountFragment) }

        //设置标题
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(
//                setOf(
//                    R.id.messageFragment,
//                    R.id.contactFragment,
//                    R.id.exploreFragment,
//                    R.id.accountFragment
//                )
                destinationMap.keys
            )
        )

        //设置底部导航的动画效果
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            //返回栈清空
            navController.popBackStack()

            destinationMap.values.forEach { it.progress = 0f }
//            messageMotionLayout.progress = 0f
//            contactMotionLayout.progress = 0f
//            exploreMotionLayout.progress = 0f
//            accountMotionLayout.progress = 0f

            destinationMap[destination.id]?.transitionToEnd()
//            when(destination.id){
//                R.id.messageFragment -> messageMotionLayout.transitionToEnd()
//                R.id.contactFragment -> contactMotionLayout.transitionToEnd()
//                R.id.exploreFragment -> exploreMotionLayout.transitionToEnd()
//                else -> accountMotionLayout.transitionToEnd()
//            }
        }
    }
}