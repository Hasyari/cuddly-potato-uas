package com.example.mobileuas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var inputNamaWisata: EditText
    private lateinit var inputKota: EditText
    private lateinit var inputDeskripsi: EditText
    private lateinit var btnSave: Button

    private lateinit var databaseHandler: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        databaseHandler = DatabaseHandler(requireContext())
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        databaseHandler.close()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        inputNamaWisata = requireView().findViewById(R.id.input_nama_wisata)
        inputKota = requireView().findViewById(R.id.input_kota)
        inputDeskripsi = requireView().findViewById(R.id.input_deskripsi)
        btnSave = requireView().findViewById(R.id.btn_simpandata)

        btnSave.setOnClickListener(View.OnClickListener {
            if (validation()){
                saveWisataData(inputNamaWisata.text.toString(), inputKota.text.toString(), inputDeskripsi.text.toString())
            }
        })
    }
    private fun saveWisataData(namaWisata: String, kotaWisata: String, deskripsiWisata: String) {
        val wisata = Wisata(namaWisata = namaWisata, kotaWisata = kotaWisata, deskripsiWisata = deskripsiWisata)
        databaseHandler.addWisata(wisata)
        Toast.makeText(requireContext(), "Wisata data saved.", Toast.LENGTH_SHORT).show()
    }
    private fun validation(): Boolean{
        var validate = false

        if (inputNamaWisata.text.toString() != "" && inputKota.text.toString() != "" && inputDeskripsi.text.toString() != ""){
            validate = true
        }else{
            validate = false
            Toast.makeText(requireContext(), "Fill the blanks", Toast.LENGTH_SHORT).show()
        }
        return validate
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}