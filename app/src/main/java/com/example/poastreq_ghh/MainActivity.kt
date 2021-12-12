package com.example.poastreq_ghh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poastreq_ghh.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding
    private lateinit var userList : ArrayList<UserItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUsers()

        binding.btnAdd.setOnClickListener {
            if (checkInput()){
                addUser(binding.etName.text.toString(),binding.etLocation.text.toString())
                binding.etName.text.clear()
                binding.etLocation.text.clear()
            }
        }
    }

    private fun checkInput():Boolean{
        return if (binding.etName.text.isEmpty() || binding.etLocation.text.isEmpty()){
            Toast.makeText(this@MainActivity, "Please fill the fields",Toast.LENGTH_LONG).show()
            false
        }else {
            true
        }
    }

    private fun addUser(name:String, location:String){
        val apiInterfacePOST = APIClient().getClient()?.create(APIInterFace::class.java)

        apiInterfacePOST?.setUsers(UserItem(0,name,location))?.enqueue(object : Callback<UserItem> {
            override fun onResponse(call: Call<UserItem>, response: Response<UserItem>) {
                Toast.makeText(this@MainActivity, "User Added successfully",Toast.LENGTH_LONG).show()
                getUsers()
                binding.mainRV.adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<UserItem>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong not able to add user",Toast.LENGTH_LONG).show()
            }

        })

    }
    private fun getUsers(){
        userList = arrayListOf()
        val apiInterfaceGET = APIClient().getClient()?.create(APIInterFace::class.java)
        apiInterfaceGET?.getUsers()?.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
               try {
                   userList = response.body()!!
                   setAdapter()
                   Log.d("MAIN", "ISSUE: successfully")

               }catch (e:Exception){
                   Log.d("MAIN", "ISSUE: $e")
               }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong not able to add user",Toast.LENGTH_LONG).show()
            }

        })
    }

    fun setAdapter(){
        binding.mainRV.adapter = MainAdapter(userList)
        binding.mainRV.layoutManager = LinearLayoutManager(this)
        binding.mainRV.setHasFixedSize(true)
    }
}