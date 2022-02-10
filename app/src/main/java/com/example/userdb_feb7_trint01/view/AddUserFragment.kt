package com.example.userdb_feb7_trint01.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.userdb_feb7_trint01.databinding.FragmentAdduserBinding
import com.example.userdb_feb7_trint01.model.UserDatabase
import com.example.userdb_feb7_trint01.model.UserEntity
import com.example.userdb_feb7_trint01.model.UserRepository
import com.example.userdb_feb7_trint01.viewmodel.UserViewModel

class AddUserFragment : Fragment() {

    private val viewModel: UserViewModel by activityViewModels {
        UserViewModel.Factory(
            UserRepository(UserDatabase.getDatabase(requireActivity().applicationContext).userDao())
        )
    }

    private var _binding : FragmentAdduserBinding? = null
    private val binding : FragmentAdduserBinding get() = _binding!!
    //var currentId = 0
    val TAG = "MainActivity"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdduserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnSubmit.setOnClickListener {
                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()
                //val newUser = UserEntity(currentId, firstName,lastName)
                val newUser = UserEntity(firstName = firstName, lastName = lastName)
                viewModel.insertUser(newUser)
                //currentId++

                Log.d(TAG, "btnSubmit Clicked")
                //Log.d(TAG, "currentId: $currentId")
                Log.d(TAG, "firstName: $firstName")
                Log.d(TAG, "lastName: $lastName")
                Log.d(TAG, "newUser: $newUser")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}