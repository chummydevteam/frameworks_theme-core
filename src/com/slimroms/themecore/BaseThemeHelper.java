/*
 * Copyright (C) 2017 SlimRoms Project
 * Copyright (C) 2017 Victor Lapin
 * Copyright (C) 2017 Griffin Millender
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
package com.slimroms.themecore;


import android.os.RemoteException;

public abstract class BaseThemeHelper extends IThemeService.Stub {
    @Override
    public int checkPermissions() throws RemoteException {
        return 0;
    }

    @Override
    public boolean isAvailable() throws RemoteException {
        return true;
    }

    @Override
    public boolean isRebootRequired() throws RemoteException {
        return false;
    }

    @Override
    public void reboot() throws RemoteException {

    }
}
