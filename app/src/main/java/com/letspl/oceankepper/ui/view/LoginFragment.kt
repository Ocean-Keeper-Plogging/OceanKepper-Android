package com.letspl.oceankepper.ui.view

import android.graphics.Paint.Join
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.letspl.oceankepper.databinding.FragmentLoginBinding
import com.letspl.oceankepper.ui.viewmodel.LoginViewModel
import com.letspl.oceankepper.util.ContextUtil
import com.letspl.oceankepper.util.loginManager.NaverLoginManager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment: Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModels()
    private val activity: BaseActivity by lazy {
        requireActivity() as BaseActivity
    }
    @Inject lateinit var naverLoginManager: NaverLoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.naverLoginManager = naverLoginManager
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModelObservers()
    }

    // viewModel 옵저버 세팅
    private fun setUpViewModelObservers() {
        loginViewModel.onNaverLoginResult.observe(viewLifecycleOwner){
            Timber.e("onNaverLoginResult $activity")
            activity.onReplaceFragment(JoinFragment())
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}