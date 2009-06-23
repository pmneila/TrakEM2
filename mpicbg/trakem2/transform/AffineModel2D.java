/**
 * License: GPL
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License 2
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * 
 * @author Stephan Saalfeld <saalfeld@mpi-cbg.de>
 *
 */
package mpicbg.trakem2.transform;

public class AffineModel2D extends mpicbg.models.AffineModel2D implements CoordinateTransform< AffineModel2D >
{

	//@Override
	final public void init( final String data )
	{
		final String[] fields = data.split( "\\s+" );
		if ( fields.length == 6 )
		{
			final float m00 = Float.parseFloat( fields[ 0 ] );
			final float m10 = Float.parseFloat( fields[ 1 ] );
			final float m01 = Float.parseFloat( fields[ 2 ] );
			final float m11 = Float.parseFloat( fields[ 3 ] );
			final float m02 = Float.parseFloat( fields[ 4 ] );
			final float m12 = Float.parseFloat( fields[ 5 ] );
			set( m00, m10, m01, m11, m02, m12 );
		}
		else throw new NumberFormatException( "Inappropriate parameters for " + this.getClass().getCanonicalName() );
	}

	//@Override
	final public String toXML( final String indent )
	{
		return indent + "<ict_transform class=\"" + this.getClass().getCanonicalName() + "\" data=\"" + toDataString() + "\"/>";
	}
	
	//@Override
	final public String toDataString()
	{
		return m00 + " " + m10 + " " + m01 + " " + m11 + " " + m02 + " " + m12;
	}
	
	@Override
	public AffineModel2D clone()
	{
		final AffineModel2D m = new AffineModel2D();
		m.m00 = m00;
		m.m01 = m01;
		m.m10 = m10;
		m.m11 = m11;

		m.m02 = m02;
		m.m12 = m12;
		
		m.cost = cost;
		
		m.invert();

		return m;
	}
	
	/**
	 * TODO Not yet tested
	 */
	//@Override
	public AffineModel2D createInverse()
	{
		final AffineModel2D ict = new AffineModel2D();
		
		ict.m00 = i00;
		ict.m10 = i10;
		ict.m01 = i01;
		ict.m11 = i11;
		ict.m02 = i02;
		ict.m12 = i12;
		
		ict.i00 = m00;
		ict.i10 = m10;
		ict.i01 = m01;
		ict.i11 = m11;
		ict.i02 = m02;
		ict.i12 = m12;
		
		ict.cost = cost;
		
		return ict;
	}
}
