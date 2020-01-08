package com.sanai.tasky;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import static android.support.v7.widget.helper.ItemTouchHelper.*;

public class SwipControllerNextDay extends ItemTouchHelper.SimpleCallback {
    private TodoTaskAdapter mAdapter;

    public SwipControllerNextDay(TodoTaskAdapter adapter) {
        super(0, ItemTouchHelper.LEFT );
        mAdapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int leftOrRight) {
        if (leftOrRight == ItemTouchHelper.LEFT) {
            int position = viewHolder.getAdapterPosition();
            mAdapter.deleteItem(position);

        }
        if (leftOrRight == ItemTouchHelper.RIGHT) {
            int position = viewHolder.getAdapterPosition();
            mAdapter.updateToDone(position );

        }




    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            // Get RecyclerView item from the ViewHolder
            View itemView = viewHolder.itemView;
            Paint p = new Paint();


            if (dX > 0) {


                p.setColor(Color.rgb(254, 220, 151));//pinck


                // Draw Rect with varying right side, equal to displacement dX
              c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                       (float) itemView.getBottom(), p);
            } else {
                /* Set your color for negative displacement */
                p.setColor(Color.rgb(255, 99, 222));//pinck

                // Draw Rect with varying left side, equal to the item's right side plus negative displacement dX
                c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                        (float) itemView.getRight(), (float) itemView.getBottom(), p);
            }

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

}