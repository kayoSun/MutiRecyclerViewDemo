package com.kayo.mutiadapter.footer;


import com.kayo.mutiadapter.MutiData;
import com.kayo.mutiadapter.MutiHolder;
import com.kayo.mutiadapter.rules.Rule;

/**
 * Created by shilei on 17/2/3.
 * <pre>
 *      脚布局规则 ，可自行实现
 *      也可使用 已简单实现的脚布局规则 {@link SimpleLoadMoreRule}
 * </pre>
 */

public abstract class LoadMoreRule<D extends MutiData, H extends MutiHolder<D>>  implements Rule<D,H> {

}
