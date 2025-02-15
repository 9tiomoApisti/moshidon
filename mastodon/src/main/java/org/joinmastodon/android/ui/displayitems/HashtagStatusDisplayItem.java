package org.joinmastodon.android.ui.displayitems;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import org.joinmastodon.android.R;
import org.joinmastodon.android.fragments.BaseStatusListFragment;
import org.joinmastodon.android.model.Hashtag;
import org.joinmastodon.android.ui.views.HashtagChartView;

public class HashtagStatusDisplayItem extends StatusDisplayItem{
	public final Hashtag tag;

	public HashtagStatusDisplayItem(String parentID, BaseStatusListFragment parentFragment, Hashtag tag){
		super(parentID, parentFragment);
		this.tag=tag;
	}

	@Override
	public Type getType(){
		return Type.HASHTAG;
	}

	public static class Holder extends StatusDisplayItem.Holder<HashtagStatusDisplayItem>{
		private final TextView title, subtitle;
		private final HashtagChartView chart;

		public Holder(Context context, ViewGroup parent){
			super(context, R.layout.item_trending_hashtag, parent);
			title=findViewById(R.id.title);
			subtitle=findViewById(R.id.subtitle);
			chart=findViewById(R.id.chart);
		}

		@Override
		public void onBind(HashtagStatusDisplayItem _item){
			Hashtag item=_item.tag;
			title.setText('#'+item.name);
			int numPeople = 0;
			if(item.history != null){
				numPeople=item.history.get(0).accounts;
				if(item.history.size()>1)
					numPeople+=item.history.get(1).accounts;
				chart.setData(item.history);
			}
			subtitle.setText(_item.parentFragment.getResources().getQuantityString(R.plurals.x_people_talking, numPeople, numPeople));
		}
	}
}
