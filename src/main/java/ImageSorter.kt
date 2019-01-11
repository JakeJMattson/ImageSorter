import java.awt.image.*
import java.io.File
import java.util.*
import javax.imageio.ImageIO

fun main(args: Array<String>) {
	val input = "input.png"
	val output = "output.png"
	val image = ImageIO.read(File(input))

	val pixels = extractPixels(image!!)
	Arrays.sort(pixels)

	val newImage = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB)
	newImage.setRGB(0, 0, image.width, image.height, pixels, 0, image.width)

	ImageIO.write(newImage, getExtension(output), File(output))
}

private fun extractPixels(fileImage: BufferedImage): IntArray {
	val image = BufferedImage(fileImage.width, fileImage.height, BufferedImage.TYPE_INT_RGB)
	image.graphics.drawImage(fileImage, 0, 0, null)
	return (image.raster.dataBuffer as DataBufferInt).data
}

private fun getExtension(fileName: String) = fileName.substring(fileName.lastIndexOf('.') + 1)