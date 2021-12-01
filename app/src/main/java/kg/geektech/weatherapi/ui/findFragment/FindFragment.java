package kg.geektech.weatherapi.ui.findFragment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weatherapi.R;
import kg.geektech.weatherapi.base.BaseFragment;
import kg.geektech.weatherapi.databinding.FragmentFindBinding;

@AndroidEntryPoint
public class FindFragment extends BaseFragment<FragmentFindBinding> {

    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //navController = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity()
                .getSupportFragmentManager().findFragmentById(R.id.nav_host);
        navController = navHostFragment.getNavController();
    }

    @Override
    protected FragmentFindBinding bind() {
        return FragmentFindBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObservers() {

    }

    @Override
    protected void setupUI() {
        binding.btnFind.setOnClickListener(v -> {
            if (Objects.requireNonNull(binding.editText.getText()).toString().isEmpty()){
                Toast.makeText(requireContext(), "Введите название города", Toast.LENGTH_SHORT).show();
            }else {
                navController.navigate((NavDirections) FindFragmentDirections
                        .actionFindFragmentToWeatherFragment(binding.editText.getText().toString()));
            }
        });
    }
}