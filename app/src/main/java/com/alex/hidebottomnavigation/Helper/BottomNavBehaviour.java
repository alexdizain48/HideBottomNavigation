package com.alex.hidebottomnavigation.Helper;


import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.View;

public class BottomNavBehaviour extends CoordinatorLayout.Behavior {

    @Override  //События вложенной прокрутки начинаются
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override  //вызывается как только прокручиваемая View была прокручена
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        child.setTranslationY(Math.max(0f,
                Math.min(Float.parseFloat(String.valueOf(child.getHeight())), child.getTranslationX()+dyConsumed)));
    }

    @Override   //будет вызван каждый раз когда что-то случится в layout, чтобы вернуть true
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {

        if (dependency instanceof Snackbar.SnackbarLayout) {  //ссылка_на_объект instanceof тип  ссылка_на_объект обозначает ссылку на экземпляр класса, а тип - конкретный тип этого класса
            updateSnackbar(child, dependency);
        }
        return super.layoutDependsOn(parent, child, dependency);
    }

    private void updateSnackbar(View child, View dependency) {  //child это элемент который усиливает поведение, dependency — тот кто будет обслуживать его как тригер для взаимодействия с child элементом

        if (dependency.getLayoutParams() instanceof  CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)dependency.getLayoutParams();

            params.setAnchorId(child.getId());
            params.anchorGravity = Gravity.TOP;
            params.gravity = Gravity.TOP;
            dependency.setLayoutParams(params);
        }
    }
}
