package com.example.flupperapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

class CustomBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {

    private var lastSelectedItemId: Int = 0
    private val iconViews = mutableMapOf<Int, View>() // Храним View иконок

    init {
        // Инициализируем иконки после загрузки меню
        post {
            for (i in 0 until menu.size()) {
                val menuItemId = menu.getItem(i).itemId
                val itemView = findViewById<View>(menuItemId)
                itemView?.let {
                    // Находим иконку внутри itemView
                    val iconView = it.findViewById<View>(androidx.appcompat.R.id.icon)
                    iconView?.let { icon ->
                        iconViews[menuItemId] = icon
                    }
                }
            }
        }

        setOnNavigationItemSelectedListener { item ->
            if (item.itemId != lastSelectedItemId) {
                animateIcon(item.itemId, true) // Анимация для новой выбранной кнопки
                if (lastSelectedItemId != 0) {
                    animateIcon(lastSelectedItemId, false) // Сбрасываем предыдущую
                }
                lastSelectedItemId = item.itemId
            }
            true
        }
    }

    private fun animateIcon(itemId: Int, isSelected: Boolean) {
        val iconView = iconViews[itemId] ?: return

        // Анимация масштабирования
        val scaleX = ObjectAnimator.ofFloat(iconView, "scaleX", if (isSelected) 1.2f else 1.0f)
        val scaleY = ObjectAnimator.ofFloat(iconView, "scaleY", if (isSelected) 1.2f else 1.0f)
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY)
        animatorSet.duration = 200
        animatorSet.start()

        // Добавление/удаление обводки
        if (isSelected) {
            iconView.setBackgroundResource(R.drawable.nav_item_background_selected)
        } else {
            iconView.background = null
        }
    }
}