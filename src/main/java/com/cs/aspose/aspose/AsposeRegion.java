package com.cs.aspose.aspose;

import com.aspose.words.IMailMergeDataSource;
import com.aspose.words.IMailMergeDataSourceRoot;

/**
 * @author Hoang DANG.
 */
public class AsposeRegion extends AbstractAsposeBean implements IMailMergeDataSourceRoot {

	private Object object;

	public AsposeRegion(AsposeContext context, Object object) {
		super(context);
		this.object = object;
	}

	@Override
	public IMailMergeDataSource getDataSource(String regionName) throws Exception {
		return new AsposeBean(context, getValueAsCollection(object, regionName), regionName);
	}

}