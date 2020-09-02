/*
 Title:        resource_attribute.java
 Description:  resource attribute name->value pair
 */

package script;


public class resource_attribute
{
	private String m_name;
	private int m_value;

	public resource_attribute(String name, int value)
	{
		m_name = name;
		m_value = value;
	}

	public String getName()
	{
		return m_name;
	}

	public int getValue()
	{
		return m_value;
	}
}

