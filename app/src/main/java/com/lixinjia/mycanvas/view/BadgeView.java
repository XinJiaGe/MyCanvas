package com.lixinjia.mycanvas.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabWidget;
import android.widget.TextView;

import com.lixinjia.mycanvas.tool.Adaptation;

/**
 * A simple text label view that can be applied as a "badge" to any given {@link android.view.View}. 
 * This class is intended to be instantiated at runtime rather than included in XML layouts.
 *
 * @author Jeff Gilfelt
 */
/**
	// 默认的badge效果
	View target = findViewById(R.id.default_target);
	BadgeView badge = new BadgeView(this, target);
	badge.setText("1");
	badge.show();

	// 设置位置
	btnPosition = (Button) findViewById(R.id.position_target);
	badge1 = new BadgeView(this, btnPosition);
	badge1.setText("12");
	badge1.setBadgePosition(BadgeView.POSITION_BOTTOM_LEFT);
	btnPosition.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			badge1.toggle();
		}
	});

	// 设置显示文本/字体颜色/背景颜色
	btnColour = (Button) findViewById(R.id.colour_target);
	badge2 = new BadgeView(this, btnColour);
	badge2.setText("New!");
	badge2.setTextColor(Color.BLUE);
	badge2.setBadgeBackgroundColor(Color.YELLOW);
	badge2.setTextSize(12);
	btnColour.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			badge2.toggle();
		}
	});

	// 默认动画效果
	btnAnim1 = (Button) findViewById(R.id.anim1_target);
	badge3 = new BadgeView(this, btnAnim1);
	badge3.setText("84");
	btnAnim1.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			badge3.toggle(true);
		}
	});

	// 用户自定义动画
	btnAnim2 = (Button) findViewById(R.id.anim2_target);
	badge4 = new BadgeView(this, btnAnim2);
	badge4.setText("123");
	badge4.setBadgePosition(BadgeView.POSITION_TOP_LEFT);
	badge4.setBadgeMargin(15);
	badge4.setBadgeBackgroundColor(Color.parseColor("#A4C639"));
	btnAnim2.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 设置进入的移动动画，设置了插值器，可以实现颤动的效果
			TranslateAnimation anim = new TranslateAnimation(-100, 0, 0, 0);
			anim.setInterpolator(new BounceInterpolator());
			// 设置动画的持续时间
			anim.setDuration(1000);
			// 设置退出的移动动画
			TranslateAnimation anim2 = new TranslateAnimation(0, -100, 0, 0);
			anim2.setDuration(500);
			badge4.toggle(anim, anim2);
		}
	});

	// 设置用户自定义的背景图片
	btnCustom = (Button) findViewById(R.id.custom_target);
	badge5 = new BadgeView(this, btnCustom);
	badge5.setText("37");
	badge5.setBackgroundResource(R.drawable.badge_ifaux);
	badge5.setTextSize(16);
	btnCustom.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			badge5.toggle(true);
		}
	});

	// 在tab上显示一个小红点
	TabWidget tabs = (TabWidget) findViewById(android.R.id.tabs);

	btnTab = (Button) findViewById(R.id.tab_btn);
	badge7 = new BadgeView(this, tabs, 2);
	badge7.setText("5");
	btnTab.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			badge7.toggle();
		}
	});

	// 可以被点击的badge
	btnClick = (Button) findViewById(R.id.click_target);
	badge6 = new BadgeView(this, btnClick);
	badge6.setText("click me");
	badge6.setBadgeBackgroundColor(Color.BLUE);
	badge6.setTextSize(16);
	//设置点击事件
	badge6.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Toast.makeText(DemoActivity.this, "clicked badge",
			Toast.LENGTH_SHORT).show();
		}
	});
	btnClick.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			badge6.toggle();
		}
	});

	// 红点数字可以自增长的badge
	btnIncrement = (Button) findViewById(R.id.increment_target);
	badge8 = new BadgeView(this, btnIncrement);
	badge8.setText("0");
	btnIncrement.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (badge8.isShown()) {
				badge8.increment(1);
			} else {
				badge8.show();
			}
		}
	});
 */


public class BadgeView extends TextView {

	public static final int POSITION_TOP_LEFT = 1;
	public static final int POSITION_TOP_RIGHT = 2;
	public static final int POSITION_BOTTOM_LEFT = 3;
	public static final int POSITION_BOTTOM_RIGHT = 4;
	
	private static final int DEFAULT_MARGIN_DIP = 5;
	private static final int DEFAULT_LR_PADDING_DIP = 5;
	private static final int DEFAULT_CORNER_RADIUS_DIP = 8;
	private static final int DEFAULT_POSITION = POSITION_TOP_RIGHT;
	private static final int DEFAULT_BADGE_COLOR = Color.RED;
	private static final int DEFAULT_TEXT_COLOR = Color.WHITE;
	
	private static Animation fadeIn;
	private static Animation fadeOut;
	
	private Context context;
	private View target;
	
	private int badgePosition;
	private int badgeMargin;
	private int badgeColor;
	
	private boolean isShown;
	
	private ShapeDrawable badgeBg;
	
	private int targetTabIndex;
	private Adaptation adaptation;

	public BadgeView(Context context) {
		this(context, (AttributeSet) null, android.R.attr.textViewStyle);
	}
	
	public BadgeView(Context context, AttributeSet attrs) {
		 this(context, attrs, android.R.attr.textViewStyle);
	}
	
	/**
     * Constructor -
     * 
     * create a new BadgeView instance attached to a target {@link android.view.View}.
     *
     * @param context context for this view.
     * @param target the View to attach the badge to.
     */
	public BadgeView(Context context, View target) {
		 this(context, null, android.R.attr.textViewStyle, target, 0);
	}

	/**
     * Constructor -
     *
     * create a new BadgeView instance attached to a target {@link android.widget.TabWidget}
     * tab at a given index.
     *
     * @param context context for this view.
     * @param target the TabWidget to attach the badge to.
     * @param index the position of the tab within the target.
     */
	public BadgeView(Context context, TabWidget target, int index) {
		this(context, null, android.R.attr.textViewStyle, target, index);
	}
	
	public BadgeView(Context context, AttributeSet attrs, int defStyle) {
		this(context, attrs, defStyle, null, 0);
	}
	
	public BadgeView(Context context, AttributeSet attrs, int defStyle, View target, int tabIndex) {
		super(context, attrs, defStyle);
		init(context, target, tabIndex);
	}

	private void init(Context context, View target, int tabIndex) {
		adaptation = new Adaptation(context);
		this.context = context;
		this.target = target;
		this.targetTabIndex = tabIndex;
		
		// apply defaults
		badgePosition = DEFAULT_POSITION;
		badgeMargin = dipToPixels(DEFAULT_MARGIN_DIP);
		badgeColor = DEFAULT_BADGE_COLOR;
		
		setTypeface(Typeface.DEFAULT_BOLD);
		int paddingPixels = dipToPixels(DEFAULT_LR_PADDING_DIP);
		setPadding(paddingPixels, 0, paddingPixels, 0);
		setTextColor(DEFAULT_TEXT_COLOR);
		
		fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator());
		fadeIn.setDuration(200);

		fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator());
		fadeOut.setDuration(200);
		
		isShown = false;
		
		if (this.target != null) {
			applyTo(this.target);
		} else {
			show();
		}
		
	}

	private void applyTo(View target) {
		
		LayoutParams lp = target.getLayoutParams();
		ViewParent parent = target.getParent();
		FrameLayout container = new FrameLayout(context);
		
		if (target instanceof TabWidget) {
			
			// set target to the relevant tab child container
			target = ((TabWidget) target).getChildTabViewAt(targetTabIndex);
			this.target = target;
			
			((ViewGroup) target).addView(container,
					new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			
			this.setVisibility(View.GONE);
			container.addView(this);
			
		} else {
			
			// TODO verify that parent is indeed a ViewGroup
			ViewGroup group = (ViewGroup) parent;
			int index = group.indexOfChild(target);
			
			group.removeView(target);
			group.addView(container, index, lp);
			
			container.addView(target);
	
			this.setVisibility(View.GONE);
			container.addView(this);
			
			group.invalidate();
			
		}
		
	}
	
	/**
     * Make the badge visible in the UI.
     * 
     */
	public void show() {
		show(false, null);
	}
	
	/**
     * Make the badge visible in the UI.
     *
     * @param animate flag to apply the default fade-in animation.
     */
	public void show(boolean animate) {
		show(animate, fadeIn);
	}
	
	/**
     * Make the badge visible in the UI.
     *
     * @param anim Animation to apply to the view when made visible.
     */
	public void show(Animation anim) {
		show(true, anim);
	}
	
	/**
     * Make the badge non-visible in the UI.
     * 
     */
	public void hide() {
		hide(false, null);
	}
	
	/**
     * Make the badge non-visible in the UI.
     *
     * @param animate flag to apply the default fade-out animation.
     */
	public void hide(boolean animate) {
		hide(animate, fadeOut);
	}
	
	/**
     * Make the badge non-visible in the UI.
     *
     * @param anim Animation to apply to the view when made non-visible.
     */
	public void hide(Animation anim) {
		hide(true, anim);
	}
	
	/**
     * Toggle the badge visibility in the UI.
     * 
     */
	public void toggle() {
		toggle(false, null, null);
	}
	
	/**
     * Toggle the badge visibility in the UI.
     * 
     * @param animate flag to apply the default fade-in/out animation.
     */
	public void toggle(boolean animate) {
		toggle(animate, fadeIn, fadeOut);
	}
	
	/**
     * Toggle the badge visibility in the UI.
     *
     * @param animIn Animation to apply to the view when made visible.
     * @param animOut Animation to apply to the view when made non-visible.
     */
	public void toggle(Animation animIn, Animation animOut) {
		toggle(true, animIn, animOut);
	}
	
	@SuppressWarnings("deprecation")
	private void show(boolean animate, Animation anim) {
		if (getBackground() == null) {
			if (badgeBg == null) {
				badgeBg = getDefaultBackground();
			}
			setBackgroundDrawable(badgeBg);
		}
		if(getText().toString().trim().equals("")){
			applyLayoutParams(adaptation.setCanvasAdaptation(15),adaptation.setCanvasAdaptation(15));
		}else{
			applyLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		}

		if (animate) {
			this.startAnimation(anim);
		}
		this.setVisibility(View.VISIBLE);
		isShown = true;
	}
	
	private void hide(boolean animate, Animation anim) {
		this.setVisibility(View.GONE);
		if (animate) {
			this.startAnimation(anim);
		}
		isShown = false;
	}
	
	private void toggle(boolean animate, Animation animIn, Animation animOut) {
		if (isShown) {
			hide(animate && (animOut != null), animOut);	
		} else {
			show(animate && (animIn != null), animIn);
		}
	}
	
	/**
     * Increment the numeric badge label. If the current badge label cannot be converted to
     * an integer value, its label will be set to "0".
     * 
     * @param offset the increment offset.
     */
	public int increment(int offset) {
		CharSequence txt = getText();
		int i;
		if (txt != null) {
			try {
				i = Integer.parseInt(txt.toString());
			} catch (NumberFormatException e) {
				i = 0;
			}
		} else {
			i = 0;
		}
		i = i + offset;
		setText(String.valueOf(i));
		return i;
	}
	
	/**
     * Decrement the numeric badge label. If the current badge label cannot be converted to
     * an integer value, its label will be set to "0".
     * 
     * @param offset the decrement offset.
     */
	public int decrement(int offset) {
		return increment(-offset);
	}
	
	private ShapeDrawable getDefaultBackground() {
		
		int r = dipToPixels(DEFAULT_CORNER_RADIUS_DIP);
		float[] outerR = new float[] {r, r, r, r, r, r, r, r};
        
		RoundRectShape rr = new RoundRectShape(outerR, null, null);
		ShapeDrawable drawable = new ShapeDrawable(rr);
		drawable.getPaint().setColor(badgeColor);
		
		return drawable;
		
	}
	
	private void applyLayoutParams(int width,int height) {
		
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, height);
		
		switch (badgePosition) {
		case POSITION_TOP_LEFT:
			lp.gravity = Gravity.LEFT | Gravity.TOP;
			lp.setMargins(badgeMargin, badgeMargin, 0, 0);
			break;
		case POSITION_TOP_RIGHT:
			lp.gravity = Gravity.RIGHT | Gravity.TOP;
			lp.setMargins(0, badgeMargin, badgeMargin, 0);
			break;
		case POSITION_BOTTOM_LEFT:
			lp.gravity = Gravity.LEFT | Gravity.BOTTOM;
			lp.setMargins(badgeMargin, 0, 0, badgeMargin);
			break;
		case POSITION_BOTTOM_RIGHT:
			lp.gravity = Gravity.RIGHT | Gravity.BOTTOM;
			lp.setMargins(0, 0, badgeMargin, badgeMargin);
			break;
		default:
			break;
		}
		
		setLayoutParams(lp);
		
	}

	/**
     * Returns the target View this badge has been attached to.
     * 
     */
	public View getTarget() {
		return target;
	}

	/**
     * Is this badge currently visible in the UI?
     * 
     */
	@Override
	public boolean isShown() {
		return isShown;
	}

	/**
     * Returns the positioning of this badge.
     * 
     * one of POSITION_TOP_LEFT, POSITION_TOP_RIGHT, POSITION_BOTTOM_LEFT, POSITION_BOTTOM_RIGHT.
     * 
     */
	public int getBadgePosition() {
		return badgePosition;
	}

	/**
     * Set the positioning of this badge.
     * 
     * @param layoutPosition one of POSITION_TOP_LEFT, POSITION_TOP_RIGHT, POSITION_BOTTOM_LEFT, POSITION_BOTTOM_RIGHT.
     * 
     */
	public void setBadgePosition(int layoutPosition) {
		this.badgePosition = layoutPosition;
	}

	/**
     * Returns the horizontal/vertical margin from the target View that is applied to this badge.
     * 
     */
	public int getBadgeMargin() {
		return badgeMargin;
	}

	/**
     * Set the horizontal/vertical margin from the target View that is applied to this badge.
     * 
     * @param badgeMargin the margin in pixels.
     */
	public void setBadgeMargin(int badgeMargin) {
		this.badgeMargin = badgeMargin;
	}
	
	/**
     * Returns the color value of the badge background.
     * 
     */
	public int getBadgeBackgroundColor() {
		return badgeColor;
	}

	/**
     * Set the color value of the badge background.
     * 
     * @param badgeColor the badge background color.
     */
	public void setBadgeBackgroundColor(int badgeColor) {
		this.badgeColor = badgeColor;
		badgeBg = getDefaultBackground();
	}
	
	private int dipToPixels(int dip) {
		Resources r = getResources();
		float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
		return (int) px;
	}

}
