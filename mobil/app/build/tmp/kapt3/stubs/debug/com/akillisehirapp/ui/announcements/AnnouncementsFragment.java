package com.akillisehirapp.ui.announcements;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0002J\b\u0010\u0017\u001a\u00020\u0011H\u0002J\b\u0010\u0018\u001a\u00020\u0011H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/akillisehirapp/ui/announcements/AnnouncementsFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "_binding", "Lcom/akillisehirapp/databinding/FragmentAnnouncementsBinding;", "binding", "getBinding", "()Lcom/akillisehirapp/databinding/FragmentAnnouncementsBinding;", "allAnnouncements", "", "Lcom/akillisehirapp/data/model/Announcement;", "adapter", "Lcom/akillisehirapp/ui/announcements/AnnouncementAdapter;", "selectedCategory", "Lcom/akillisehirapp/data/model/AnnouncementCategory;", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupFilters", "filterAnnouncements", "onDestroyView", "app_debug"})
public final class AnnouncementsFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.akillisehirapp.databinding.FragmentAnnouncementsBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.akillisehirapp.data.model.Announcement> allAnnouncements = null;
    private com.akillisehirapp.ui.announcements.AnnouncementAdapter adapter;
    @org.jetbrains.annotations.Nullable()
    private com.akillisehirapp.data.model.AnnouncementCategory selectedCategory;
    
    public AnnouncementsFragment() {
        super();
    }
    
    private final com.akillisehirapp.databinding.FragmentAnnouncementsBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupFilters() {
    }
    
    private final void filterAnnouncements() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}