/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgk.game.system;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 *
 * @author cgmcandrews
 */
public class Asset<g> {
	String fileName;
	Class<g> myClass;
	g cachedAsset;

	public Asset(String fileName, Class<g> aClass) {
		myClass = aClass;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public Class<g> getAssetClass() {
		return myClass;
	}

	public void setCachedAsset(g cachedAsset) {
		this.cachedAsset = cachedAsset;
	}

	@SuppressWarnings("unchecked")
	public AtlasRegion getAssetFromAtlas(TextureAtlas atlas) {
		if (myClass == Texture.class) {
			if (cachedAsset == null) {
				cachedAsset = (g) atlas.findRegion(fileName);
			}
			return (AtlasRegion) cachedAsset;
		} else {
			System.out.println("Asset is not a texture! Instead found |"
					+ myClass.toString() + "|");
			return null;
		}
	}
}
