package com.example.assignment2_2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment2_2.databinding.FragmentChatBinding;
import com.example.assignment2_2.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Stack;


public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private  String[] titles = {"messages", "Calls"};

    private Stack<Integer> tabStack = new Stack<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity());
        binding.viewPager.setAdapter(adapter);

        new TabLayoutMediator(binding.tabs, binding.viewPager, (tab, position) -> {
            tab.setText(titles[position]);
        }).attach();


        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (!tabStack.isEmpty() && tabStack.peek() == position) return;
                tabStack.push(position);
            }
        });


        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new androidx.activity.OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        if (tabStack.size() > 1) {
                            tabStack.pop();
                            binding.viewPager.setCurrentItem(tabStack.peek(), true);
                        } else {
                            setEnabled(false);
                            requireActivity().onBackPressed();
                        }
                    }
                });


    }
}