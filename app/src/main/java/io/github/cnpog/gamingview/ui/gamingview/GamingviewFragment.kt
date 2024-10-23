package io.github.cnpog.gamingview.ui.gamingview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import io.github.cnpog.gamingview.R
import io.github.cnpog.gamingview.databinding.FragmentGamingviewBinding
import io.github.cnpog.gamingview.settings.WideVisionWriter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GamingviewFragment : Fragment() {

    private var _binding: FragmentGamingviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var gamingViewModel: GamingviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        gamingViewModel = ViewModelProvider(this).get(GamingviewViewModel::class.java)

        _binding = FragmentGamingviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        loadInstalledApps()
        return root
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        gamingViewModel.installedApps.observe(viewLifecycleOwner, Observer { appList ->
            val adapter = AppListAdapter(requireContext(), appList) { appName ->
                showAppDetails(appName)
            }
            recyclerView.adapter = adapter
        })
    }

    private fun loadInstalledApps() {
        val packageManager = requireActivity().packageManager
        gamingViewModel.loadInstalledApps(packageManager)
    }

    private fun showAppDetails(appName: String) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_app_details, null)
        val tabLayout: TabLayout = dialogView.findViewById(R.id.tab_layout)
        val viewPager: ViewPager2 = dialogView.findViewById(R.id.view_pager)
        val confirmButton: Button = dialogView.findViewById(R.id.confirm_button)

        val adapter = TabAdapter(requireActivity())
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "MOBA"
                1 -> "Shooter"
                else -> null
            }
        }.attach()

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle(appName)
            .setNegativeButton("Close") { dialog, _ -> dialog.dismiss() }

        val dialog = dialogBuilder.create()

        confirmButton.setOnClickListener {
            val currentTabPosition = viewPager.currentItem

            if (isAllRadioGroupsSelected(dialogView, currentTabPosition)) {
                val selectedOptions = collectSelectedOptions(dialogView) // Modify to accept dialogView
                val wideVisionWriter = WideVisionWriter(requireContext())
                wideVisionWriter.processSelectedOptions(selectedOptions, appName)
                dialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "Please select an option from each group in the current tab.", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }


    private fun isAllRadioGroupsSelected(dialogView: View, tabPosition: Int): Boolean {
        return when (tabPosition) {
            0 -> {
                val tabOneRadioGroupTop: RadioGroup = dialogView.findViewById(R.id.radio_group_top)
                val tabOneRadioGroupBottom: RadioGroup = dialogView.findViewById(R.id.radio_group_bottom)

                if (tabOneRadioGroupTop.checkedRadioButtonId == -1) {
                    return false
                }

                if (tabOneRadioGroupBottom.checkedRadioButtonId == -1) {
                    return false
                }
                true
            }
            1 -> {
                val tabTwoRadioGroup: RadioGroup = dialogView.findViewById(R.id.radio_group)
                tabTwoRadioGroup.checkedRadioButtonId != -1
            }
            else -> false
        }
    }


    private fun collectSelectedOptions(dialogView: View): Map<String, String> {
        val selectedOptions = mutableMapOf<String, String>()
        val currentTabPosition = dialogView.findViewById<ViewPager2>(R.id.view_pager).currentItem

        if (currentTabPosition == 0) {
            val tabOneRadioGroupTop: RadioGroup = dialogView.findViewById(R.id.radio_group_top)
            val selectedTopId = tabOneRadioGroupTop.checkedRadioButtonId
            if (selectedTopId != -1) {
                val selectedTopButton = tabOneRadioGroupTop.findViewById<RadioButton>(selectedTopId)
                selectedOptions["position"] = selectedTopButton.tag.toString()
            }

            val tabOneRadioGroupBottom: RadioGroup = dialogView.findViewById(R.id.radio_group_bottom)
            val selectedBottomId = tabOneRadioGroupBottom.checkedRadioButtonId
            if (selectedBottomId != -1) {
                val selectedBottomButton = tabOneRadioGroupBottom.findViewById<RadioButton>(selectedBottomId)
                selectedOptions["mode"] = selectedBottomButton.tag.toString()
            }
        } else if (currentTabPosition == 1) {
            val tabTwoRadioGroup: RadioGroup = dialogView.findViewById(R.id.radio_group)
            val selectedSecondTabId = tabTwoRadioGroup.checkedRadioButtonId
            if (selectedSecondTabId != -1) {
                val selectedSecondButton = tabTwoRadioGroup.findViewById<RadioButton>(selectedSecondTabId)
                selectedOptions["mode"] = selectedSecondButton.tag.toString()
            }
        }

        return selectedOptions
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
