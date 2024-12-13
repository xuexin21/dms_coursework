package com.example.demo.model;

/**
 * The Destructible interface represents entities that can take damage and be destroyed.
 * Any class implementing this interface must provide behavior for taking damage and handling destruction.
 */
public interface Destructible {

	/**
	 * This method is called when the entity takes damage. The exact behavior
	 * of how damage is applied is defined in the implementing class.
	 */
	void takeDamage();

	/**
	 * This method is called to destroy the entity. The exact behavior of destruction
	 * (e.g., marking the entity as destroyed) is defined in the implementing class.
	 */
	void destroy();
}
