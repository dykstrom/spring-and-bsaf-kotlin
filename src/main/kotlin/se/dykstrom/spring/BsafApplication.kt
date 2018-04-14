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

import org.jdesktop.application.Application
import org.jdesktop.application.SingleFrameApplication
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import java.awt.Component

class BsafApplication : SingleFrameApplication() {

    /**
     * Injects resources into all components below the given `root` component.
     */
    inline fun <reified T> injectComponents(root: Component) = context.getResourceMap(T::class.java).injectComponents(root)

    override fun startup() = show(context().getBean(BsafFrameView::class.java))

    /**
     * Creates and initializes the Spring application context. This method can only be called once,
     * and only _after_ the application class has been created. It cannot be called in the constructor.
     */
    private fun context() = GenericApplicationContext().apply {
        beans {
            bean { Application.getInstance() as BsafApplication }
            bean { SwingDialog(ref()) }
            bean { BsafFrameView(ref(), ref()) }
        }.initialize(this)
        refresh()
    }
}

fun main(args: Array<String>) {
    Application.launch(BsafApplication::class.java, args)
}
