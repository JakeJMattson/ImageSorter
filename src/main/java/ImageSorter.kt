
import java.awt.image.*
import java.io.File
import java.util.*
import javax.imageio.ImageIO

object ImageSorter {
	fun main(args: Array<String>) {
		val file = File("image.png")
		val image = readImage(file)
		val pixels = extractPixels(image!!)

		Arrays.sort(pixels)

		val newImage = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_RGB)
		newImage.setRGB(0, 0, image.width, image.height, pixels, 0, image.width)

		writeImage(newImage)
	}

	private fun extractPixels(fileImage: BufferedImage): IntArray {
		val image = BufferedImage(fileImage.width, fileImage.height, BufferedImage.TYPE_INT_RGB)
		image.graphics.drawImage(fileImage, 0, 0, null)
		return (image.raster.dataBuffer as DataBufferInt).data
	}

	private fun readImage(file: File) = ImageIO.read(file)

	private fun writeImage(image: BufferedImage) = ImageIO.write(image, "png", File("output.png"))
}
