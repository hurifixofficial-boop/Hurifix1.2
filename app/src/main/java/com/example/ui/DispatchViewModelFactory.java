package com.example.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.example.BuildConfig;
import com.example.data.repository.DispatchRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* compiled from: DispatchViewModel.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010\u0006\u001a\u0002H\u0007\"\b\b\u0000\u0010\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\nH\u0016¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/example/ui/DispatchViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "repository", "Lcom/example/data/repository/DispatchRepository;", "<init>", "(Lcom/example/data/repository/DispatchRepository;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app"}, k = BuildConfig.VERSION_CODE, mv = {2, 2, 0}, xi = 48)
/* loaded from: /tmp/app_dex/classes8.dex */
public final class DispatchViewModelFactory implements ViewModelProvider.Factory {
    public static final int $stable = 8;
    private final DispatchRepository repository;

    public DispatchViewModelFactory(DispatchRepository repository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }

    public <T extends ViewModel> T create(Class<T> cls, CreationExtras creationExtras) {
        return (T) super.create(cls, creationExtras);
    }

    public <T extends ViewModel> T create(KClass<T> kClass, CreationExtras creationExtras) {
        return (T) super.create(kClass, creationExtras);
    }

    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(DispatchViewModel.class)) {
            return new DispatchViewModel(this.repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
