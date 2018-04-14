/*
 * Copyright (C) 2018 Johan Dykstrom
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.dykstrom.spring

import org.jdesktop.application.Action
import org.jdesktop.application.FrameView
import java.awt.BorderLayout
import java.awt.Dimension
import javax.inject.Inject
import javax.swing.border.EmptyBorder

class BsafFrameView
@Inject constructor(private val application: BsafApplication, private val dialog: SwingDialog) : FrameView(application) {

    init {
        val actions = application.context.getActionMap(this)

        component = panel(name = "panel") {
            layout = BorderLayout()
            border = EmptyBorder(100, 150, 100, 150)
            preferredSize = Dimension(500, 300)

            button(name = "button") {
                +BorderLayout.CENTER
                action = actions.get("openDialog")
            }
        }

        application.injectComponents<BsafFrameView>(component)
    }

    @Action
    fun openDialog() = application.show(dialog)
}
