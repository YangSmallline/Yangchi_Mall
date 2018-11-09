package cn.yangchi.chichi_core.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.internal.widget.ContentFrameLayout;

import cn.yangchi.chichi_core.R;
import cn.yangchi.chichi_core.delegates.BaseDelegate;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class ProxyActivity extends SupportActivity {

    public abstract BaseDelegate setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
