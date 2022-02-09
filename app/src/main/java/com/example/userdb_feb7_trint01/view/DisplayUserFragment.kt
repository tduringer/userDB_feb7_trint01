package com.example.userdb_feb7_trint01.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userdb_feb7_trint01.databinding.FragmentDisplayusersBinding
import com.example.userdb_feb7_trint01.model.UserDatabase
import com.example.userdb_feb7_trint01.model.UserRepository
import com.example.userdb_feb7_trint01.viewmodel.UserViewModel

class DisplayUserFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels {
        UserViewModel.Factory(
            UserRepository(UserDatabase.getDatabase(requireActivity().applicationContext).userDao())
        )
    }

    private var _binding : FragmentDisplayusersBinding? = null
    private val binding : FragmentDisplayusersBinding get() = _binding!!

    private val userAdapter by lazy {
        UserAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDisplayusersBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.users.observe(viewLifecycleOwner) {
            with(binding) {
                rvUsers.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = userAdapter
                }
                userAdapter.submitUsers(it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}