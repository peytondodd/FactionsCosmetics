/*
 * Copyright (C) 2016 Daniel Saukel
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
package io.github.dre2n.factionscosmetics;

import com.massivecraft.factions.Patch;
import io.github.dre2n.commons.compatibility.Internals;
import io.github.dre2n.commons.javaplugin.BRPlugin;
import io.github.dre2n.commons.javaplugin.BRPluginSettings;
import io.github.dre2n.commons.util.messageutil.MessageUtil;
import io.github.dre2n.factionscosmetics.config.FCConfig;
import io.github.dre2n.factionscosmetics.listener.FactionsListener;
import io.github.dre2n.factionscosmetics.listener.PlayerListener;
import java.io.File;

/**
 * @author Daniel Saukel
 */
public class FactionsCosmetics extends BRPlugin {

    private static FactionsCosmetics instance;

    private FCConfig config;

    public FactionsCosmetics() {
        settings = new BRPluginSettings(false, false, false, false, Internals.INDEPENDENT);
    }

    @Override
    public void onEnable() {
        try {
            MessageUtil.log("Successfully hooked into " + Patch.getFullName() + ".");

        } catch (Exception exception) {
            MessageUtil.log("Could not find dependencies!");
            manager.disablePlugin(this);
            return;
        }

        super.onEnable();

        instance = this;

        loadFCConfig(new File(getDataFolder(), "config.yml"));

        manager.registerEvents(new PlayerListener(), this);
        manager.registerEvents(new FactionsListener(), this);
    }

    /**
     * @return the plugin instance
     */
    public static FactionsCosmetics getInstance() {
        return instance;
    }

    /**
     * @return the loaded instance of FCConfig
     */
    public FCConfig getFCConfig() {
        return config;
    }

    /**
     * load / reload a new instance of FCConfig
     */
    public void loadFCConfig(File file) {
        config = new FCConfig(file);
    }

}
