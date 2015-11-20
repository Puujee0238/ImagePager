package com.celerysoft.imagepager.adapter;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.celerysoft.imagepager.BuildConfig;
import com.celerysoft.imagepager.ImagePager;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Base adapter of ImagePager
 * Created by Celery on 2015-11-18.
 */
public abstract class ImagePagerAdapter extends PagerAdapter {

    @SuppressWarnings("unused")
    private final String TAG = this.getClass().getSimpleName();
    @SuppressWarnings("unused")
    private final boolean DEBUG = BuildConfig.DEBUG;

    private ArrayList<PhotoView> mImageViews = new ArrayList<>();
    private PhotoView mCurrentPrimaryItem = null;

    private ImagePager.OnImageClickListener mOnPhotoTapListener;
    public void setOnImageClickListener(ImagePager.OnImageClickListener onImageClickListener) {
        mOnPhotoTapListener = onImageClickListener;
    }

    private ImagePager.OnImageClickListener mOnPageClickListener;
    public void setOnPageClickListenerListener(ImagePager.OnImageClickListener onImageClickListener) {
        mOnPageClickListener = onImageClickListener;
    }

    public ImagePagerAdapter() {
        super();
    }

    public abstract PhotoView getItem(int position);

    @Override
    public abstract int getCount();

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        if (mImageViews.size() > position) {
//            PhotoView v = mImageViews.get(position);
//            if (v != null) {
//                container.addView(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                return v;
//            }
//        }
//
//        PhotoView imageView = getItem(position);
//        while (mImageViews.size() <= position) {
//            mImageViews.add(null);
//        }
//        imageView.setVisibility(View.VISIBLE);
//        mImageViews.set(position, imageView);
//
//        container.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//
//        return imageView;
        PhotoView imageView = getItem(position);
        imageView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                mOnPageClickListener.onImageClick();
            }
        });
        imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                mOnPageClickListener.onImageClick();
                if (mOnPhotoTapListener != null) {
                    mOnPhotoTapListener.onImageClick();
                }
            }
        });

        container.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        PhotoView imageView = (PhotoView) object;
//        if (imageView != null) {
//            mImageViews.remove(imageView);
//        }
        container.removeView((View) object);
    }

//    @Override
//    public void setPrimaryItem(ViewGroup container, int position, Object object) {
//        PhotoView imageView = (PhotoView) object;
//        if (imageView != mCurrentPrimaryItem) {
//            if (mCurrentPrimaryItem != null) {
//                mCurrentPrimaryItem.setVisibility(View.INVISIBLE);
//            }
//
//            if (imageView != null) {
//                imageView.setVisibility(View.VISIBLE);
//            }
//
//            mCurrentPrimaryItem = imageView;
//        }
//    }

    @Override
    public int getItemPosition(Object object) {
        PhotoView imageView = (PhotoView) object;
        int positon = mImageViews.indexOf(imageView);
        if (positon == -1) {
            return POSITION_NONE;
        } else {
            return positon;
        }
    }

    @Override
    public Parcelable saveState() {
        //TODO
        return super.saveState();
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        //TODO
        super.restoreState(state, loader);
    }

}