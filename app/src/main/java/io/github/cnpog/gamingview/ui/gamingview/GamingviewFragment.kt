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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.github.cnpog.gamingview.R
import io.github.cnpog.gamingview.databinding.FragmentGamingviewBinding
import io.github.cnpog.gamingview.settings.WideVisionWriter

class GamingviewFragment : Fragment() {

    private var _binding: FragmentGamingviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var gamingViewModel: GamingviewViewModel
    private lateinit var appListAdapter: AppListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGamingviewBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize ViewModel with a Factory to pass Context
        val factory = GamingviewViewModelFactory(requireContext())
        gamingViewModel = ViewModelProvider(this, factory)[GamingviewViewModel::class.java]

        setupRecyclerView()
        gamingViewModel.loadInstalledApps()
        return root
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        // Initialize the adapter
        appListAdapter = AppListAdapter(requireContext()) { appItem ->
            showAppDetails(appItem)
        }
        recyclerView.adapter = appListAdapter

        // Observe the LiveData and update the adapter's data
        gamingViewModel.installedApps.observe(viewLifecycleOwner) { installedApps ->
            if (installedApps.isEmpty()) {
                recyclerView.visibility = View.GONE
            } else {
                recyclerView.visibility = View.VISIBLE
                appListAdapter.updateData(installedApps)
            }
        }
    }


    private fun showAppDetails(appItem: AppItem) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_app_details, null)
        val tabLayout: TabLayout = dialogView.findViewById(R.id.tab_layout)
        val viewPager: ViewPager2 = dialogView.findViewById(R.id.view_pager)
        val confirmButton: Button = dialogView.findViewById(R.id.confirm_button)

        val adapter = TabAdapter(requireActivity())
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tab_header_moba)
                1 -> getString(R.string.tab_header_shooter)
                2 -> getString(R.string.tab_header_vertical)
                else -> null
            }
        }.attach()

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle(appItem.applicationInfo.packageName)
            .setNegativeButton(getString(R.string.close)) { dialog, _ -> dialog.dismiss() }

        val dialog = dialogBuilder.create()

        confirmButton.setOnClickListener {
            val currentTabPosition = viewPager.currentItem

            if (isAllRadioGroupsSelected(dialogView, currentTabPosition)) {
                val selectedOptions = collectSelectedOptions(dialogView)
                val wideVisionWriter = WideVisionWriter(requireContext())
                wideVisionWriter.processSelectedOptions(selectedOptions, appItem.applicationInfo.packageName)
                gamingViewModel.loadInstalledApps()
                dialog.dismiss()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.please_select_option),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        dialog.show()
    }

    private fun isAllRadioGroupsSelected(dialogView: View, tabPosition: Int): Boolean {
        return when (tabPosition) {
            0 -> {
                val tabOneRadioGroupTop: RadioGroup = dialogView.findViewById(R.id.radio_group_top)
                val tabOneRadioGroupBottom: RadioGroup = dialogView.findViewById(R.id.radio_group_bottom)

                tabOneRadioGroupTop.checkedRadioButtonId != -1 &&
                        tabOneRadioGroupBottom.checkedRadioButtonId != -1
            }
            1 -> {
                val tabTwoRadioGroup: RadioGroup = dialogView.findViewById(R.id.radio_group)
                tabTwoRadioGroup.checkedRadioButtonId != -1
            }
            2 -> {
                val tabThreeRadioGroupTop: RadioGroup = dialogView.findViewById(R.id.radio_group_top)
                val tabThreeRadioGroupBottom: RadioGroup = dialogView.findViewById(R.id.radio_group_bottom)

                tabThreeRadioGroupTop.checkedRadioButtonId != -1 &&
                        tabThreeRadioGroupBottom.checkedRadioButtonId != -1
            }
            else -> false
        }
    }

    private fun collectSelectedOptions(dialogView: View): Map<String, String> {
        val selectedOptions = mutableMapOf<String, String>()
        val viewPager: ViewPager2 = dialogView.findViewById(R.id.view_pager)
        val currentTabPosition = viewPager.currentItem

        when (currentTabPosition) {
            0 -> {
                val tabOneRadioGroupTop: RadioGroup = dialogView.findViewById(R.id.radio_group_top)
                val selectedTopId = tabOneRadioGroupTop.checkedRadioButtonId
                if (selectedTopId != -1) {
                    val selectedTopButton =
                        tabOneRadioGroupTop.findViewById<RadioButton>(selectedTopId)
                    selectedOptions["position"] = selectedTopButton.tag.toString()
                }

                val tabOneRadioGroupBottom: RadioGroup =
                    dialogView.findViewById(R.id.radio_group_bottom)
                val selectedBottomId = tabOneRadioGroupBottom.checkedRadioButtonId
                if (selectedBottomId != -1) {
                    val selectedBottomButton =
                        tabOneRadioGroupBottom.findViewById<RadioButton>(selectedBottomId)
                    selectedOptions["mode"] = selectedBottomButton.tag.toString()
                }
            }
            1 -> {
                val tabTwoRadioGroup: RadioGroup = dialogView.findViewById(R.id.radio_group)
                val selectedSecondTabId = tabTwoRadioGroup.checkedRadioButtonId
                if (selectedSecondTabId != -1) {
                    val selectedSecondButton =
                        tabTwoRadioGroup.findViewById<RadioButton>(selectedSecondTabId)
                    selectedOptions["mode"] = selectedSecondButton.tag.toString()
                }
            }
            2 -> {
                val tabThreeRadioGroupTop: RadioGroup = dialogView.findViewById(R.id.radio_group_top)
                val selectedTopId = tabThreeRadioGroupTop.checkedRadioButtonId
                if (selectedTopId != -1) {
                    val selectedTopButton =
                        tabThreeRadioGroupTop.findViewById<RadioButton>(selectedTopId)
                    selectedOptions["position"] = selectedTopButton.tag.toString()
                }

                val tabThreeRadioGroupBottom: RadioGroup =
                    dialogView.findViewById(R.id.radio_group_bottom)
                val selectedBottomId = tabThreeRadioGroupBottom.checkedRadioButtonId
                if (selectedBottomId != -1) {
                    val selectedBottomButton =
                        tabThreeRadioGroupBottom.findViewById<RadioButton>(selectedBottomId)
                    selectedOptions["mode"] = selectedBottomButton.tag.toString()
                }
            }
        }

        return selectedOptions
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (::appListAdapter.isInitialized) {
            appListAdapter.clear()
        }
    }
}
